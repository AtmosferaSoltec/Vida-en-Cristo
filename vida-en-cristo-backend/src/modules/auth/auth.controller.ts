import { Body, Controller, HttpCode, HttpStatus, Post } from '@nestjs/common';
import { AuthService } from './auth.service';
import { LoginDto } from './dto/login.dto';
import { RegisterDto } from './dto/register.dto';

@Controller('auth')
export class AuthController {
  constructor(private readonly authService: AuthService) {}

  @HttpCode(HttpStatus.OK)
  @Post("login")
  login(@Body() dto: LoginDto) {
    return this.authService.login(dto);
  }
  
  @HttpCode(HttpStatus.OK)
  @Post("register")
  register(@Body() dto: RegisterDto) {
    return this.authService.register(dto);
  }
}
