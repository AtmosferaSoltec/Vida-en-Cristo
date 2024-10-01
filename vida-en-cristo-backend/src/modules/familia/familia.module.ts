import { Module } from '@nestjs/common';
import { FamiliaService } from './familia.service';
import { FamiliaController } from './familia.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { Familia } from './entities/familia.entity';

@Module({
  imports: [TypeOrmModule.forFeature([Familia])],
  controllers: [FamiliaController],
  providers: [FamiliaService],
})
export class FamiliaModule {}
