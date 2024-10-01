import { Injectable, UnauthorizedException } from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';
import { UsuarioService } from '../usuario/usuario.service';
import { LoginDto } from './dto/login.dto';
import { RegisterDto } from './dto/register.dto';
import * as bcrypt from 'bcrypt';
import { constants } from 'src/config/constants';

@Injectable()
export class AuthService {
  constructor(
    private jwtService: JwtService,
    private readonly userService: UsuarioService,
  ) {}

  async login(dto: LoginDto) {
    const user = await this.userService.findOneByUser(dto.usuario);

    const isMatch = await bcrypt.compare(dto.clave, user.clave);

    if (!isMatch) {
      throw new UnauthorizedException('Credenciales inválidas');
    }

    const payload = { id: user.id, nombre: user.nombre, usuario: user.usuario };
    const token = await this.jwtService.signAsync(payload);
    return {
      access_token: token,
      token_type: 'bearer',
      expires_in: constants.expires,
    };
  }

  async register(dto: RegisterDto) {
    const hash = await bcrypt.hash(dto.clave, 10);
    return await this.userService.create({
      ...dto,
      clave: hash,
    });
  }
}
