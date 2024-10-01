import {
  ConflictException,
  Injectable,
  NotFoundException,
} from '@nestjs/common';
import { CreateDistritoDto } from './dto/create-distrito.dto';
import { UpdateDistritoDto } from './dto/update-distrito.dto';
import { Repository } from 'typeorm';
import { Distrito } from './entities/distrito.entity';
import { InjectRepository } from '@nestjs/typeorm';

@Injectable()
export class DistritoService {
  constructor(
    @InjectRepository(Distrito)
    private readonly repo: Repository<Distrito>,
  ) {}

  async create({ nombre }: CreateDistritoDto) {
    const ifExist = await this.findByNombre(nombre);
    if (ifExist) throw new ConflictException(`Distrito ${nombre} ya existe`);
    const distrito = this.repo.create({ nombre });
    await this.repo.save(distrito);
    return distrito;
  }

  async findAll() {
    return await this.repo.find();
  }

  async findByNombre(nombre: string) {
    return await this.repo.findOne({ where: { nombre } });
  }

  async findOne(id: number) {
    const distrito = await this.repo.findOne({ where: { id } });
    if (!distrito) throw new NotFoundException(`Distrito #${id} no encontrado`);
    return distrito;
  }

  async update(id: number, { nombre }: UpdateDistritoDto) {
    const distrito = await this.findOne(id);

    if (nombre !== distrito.nombre) {
      const ifExist = await this.findByNombre(nombre);
      if (ifExist) throw new ConflictException(`Distrito ${nombre} ya existe`);
    }

    const newDistrito = await this.repo.preload({ id, nombre });
    if (!newDistrito)
      throw new NotFoundException(`No se pudo cargar el distrito con ID ${id}`);

    await this.repo.save(newDistrito);
    return this.findOne(id);
  }

  async remove(id: number) {
    const distrito = await this.findOne(id);
    await this.repo.delete(distrito);
    return;
  }
}
