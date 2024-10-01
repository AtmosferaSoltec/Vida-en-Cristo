import { Module } from '@nestjs/common';
import { MiembroService } from './miembro.service';
import { MiembroController } from './miembro.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Miembro } from './entities/miembro.entity';
import { DistritoModule } from '../distrito/distrito.module';

@Module({
  imports: [TypeOrmModule.forFeature([Miembro]), DistritoModule],
  controllers: [MiembroController],
  providers: [MiembroService],
})
export class MiembroModule {}
