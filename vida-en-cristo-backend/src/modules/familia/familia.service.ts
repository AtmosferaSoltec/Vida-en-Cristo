import { Injectable, NotFoundException } from '@nestjs/common';
import { CreateFamiliaDto } from './dto/create-familia.dto';
import { UpdateFamiliaDto } from './dto/update-familia.dto';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Familia } from './entities/familia.entity';

@Injectable()
export class FamiliaService {
  constructor(
    @InjectRepository(Familia)
    private readonly repo: Repository<Familia>,
  ) {}
  create(createFamiliaDto: CreateFamiliaDto) {
    return 'This action adds a new familia';
  }

  async findAll() {
    const list = await this.repo.find({ relations: ['miembros'] });
    const listFilter = list.map((f) => ({
      ...f,
      miembros: f.miembros.map((m) => ({ ...m, distrito: m.distrito.nombre })),
    }));
    return listFilter;
  }

  async findOne(id: number) {
    const familia = await this.repo.findOne({ where: { id } , relations: ['miembros'] });
    if (!familia) {
      throw new NotFoundException(`Familia #${id} not found`);
    }
    return familia;
  }

  update(id: number, updateFamiliaDto: UpdateFamiliaDto) {
    return `This action updates a #${id} familia`;
  }

  remove(id: number) {
    return `This action removes a #${id} familia`;
  }
}
