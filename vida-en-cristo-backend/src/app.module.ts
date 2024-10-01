import { Module } from '@nestjs/common';
import { MiembroModule } from './modules/miembro/miembro.module';
import { ConfigModule } from '@nestjs/config';
import { TypeOrmModule } from '@nestjs/typeorm';
import { UsuarioModule } from './modules/usuario/usuario.module';
import { AuthModule } from './modules/auth/auth.module';
import { DistritoModule } from './modules/distrito/distrito.module';
import { FamiliaModule } from './modules/familia/familia.module';

@Module({
  imports: [
    ConfigModule.forRoot(),
    TypeOrmModule.forRoot({
      type: 'mysql',
      host: process.env.DB_HOST,
      port: +process.env.DB_PORT,
      username: process.env.DB_USERNAME,
      password: process.env.DB_PASSWORD,
      database: process.env.DB_DATABASE,
      autoLoadEntities: true,
      synchronize: true,
    }),
    MiembroModule,
    UsuarioModule,
    AuthModule,
    DistritoModule,
    FamiliaModule,
  ],
  controllers: [],
  providers: [],
})
export class AppModule {}
