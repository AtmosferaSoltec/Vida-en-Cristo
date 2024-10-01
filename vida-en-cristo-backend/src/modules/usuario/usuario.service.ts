import {
  ConflictException,
  HttpException,
  HttpStatus,
  Injectable,
  NotFoundException,
} from '@nestjs/common';
import { Repository } from 'typeorm';
import { Usuario } from './entities/usuario.entity';
import { InjectRepository } from '@nestjs/typeorm';
import { TipoUsuario } from './entities/tipo-usuario.entity';
import { RegisterDto } from '../auth/dto/register.dto';

@Injectable()
export class UsuarioService {
  constructor(
    @InjectRepository(Usuario)
    private readonly repo: Repository<Usuario>,

    @InjectRepository(TipoUsuario)
    private readonly repoTipoUser: Repository<TipoUsuario>,
  ) {}

  async create(dto: RegisterDto) {
    await this.ifExistUser(dto.usuario);
    const tipoUser = await this.findTipoUser(dto.tipoUsuario);

    const createUser = this.repo.create({
      ...dto,
      tipoUsuario: tipoUser,
    });

    await this.repo.save(createUser);

    return createUser;
  }

  async listarUsuarios() {
    const list = (await this.repo.find()).map((user) => {
      return { ...user, tipoUsuario: user.tipoUsuario.nombre };
    });
    return list;
  }

  private async ifExistUser(usuario: string) {
    const user = await this.repo.findOne({ where: { usuario } });
    if (user) {
      throw new ConflictException('Usuario ya existe');
    }
    return;
  }

  private async findTipoUser(id: number) {
    const tipoUser = await this.repoTipoUser.findOne({ where: { id } });
    if (!tipoUser) {
      throw new NotFoundException('Tipo de usuario no existe');
    }
    return tipoUser;
  }

  async findOneByUser(usuario: string) {
    const user = await this.repo.findOne({ where: { usuario } });
    if (!user) {
      throw new NotFoundException('Usuario no encontrado');
    }
    return user;
  }

  async findOne(id: number) {
    return `This action returns a #${id} usuario`;
  }

  async remove(id: number) {
    const user = await this.findOne(id);
    await this.repo.delete(user);
    throw new HttpException('Usuario eliminado', HttpStatus.OK);
  }
}
