import { Module } from '@nestjs/common';
import { UsuarioService } from './usuario.service';
import { UsuarioController } from './usuario.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { TipoUsuario } from './entities/tipo-usuario.entity';
import { Usuario } from './entities/usuario.entity';

@Module({
  imports: [TypeOrmModule.forFeature([TipoUsuario, Usuario])],
  controllers: [UsuarioController],
  providers: [
    UsuarioService
  ],
  exports: [UsuarioService],
})
export class UsuarioModule {}
