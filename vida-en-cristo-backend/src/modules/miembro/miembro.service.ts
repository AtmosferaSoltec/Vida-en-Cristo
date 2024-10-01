import {
  ConflictException,
  Injectable,
  InternalServerErrorException,
  NotFoundException,
} from '@nestjs/common';
import { CreateMiembroDto } from './dto/create-miembro.dto';
import { UpdateMiembroDto } from './dto/update-miembro.dto';
import { DataSource, Repository } from 'typeorm';
import { Miembro } from './entities/miembro.entity';
import { InjectRepository } from '@nestjs/typeorm';
import { DistritoService } from '../distrito/distrito.service';
import { Distrito } from '../distrito/entities/distrito.entity';

@Injectable()
export class MiembroService {
  constructor(
    @InjectRepository(Miembro)
    private readonly repo: Repository<Miembro>,
    private readonly distritoService: DistritoService,
    private readonly dataSource: DataSource,
  ) {}

  async create(createMiembroDto: CreateMiembroDto) {
    const ifExist = await this.findByDni(createMiembroDto.dni);
    if (ifExist) {
      throw new ConflictException(
        `Miembro con DNI ${createMiembroDto.dni} ya existe`,
      );
    }

    let distrito: Distrito | null = null;
    if (createMiembroDto.id_distrito) {
      distrito = await this.distritoService.findOne(
        createMiembroDto.id_distrito,
      );
    }

    const miembro = this.repo.create({
      ...createMiembroDto,
      distrito: distrito,
    });
    await this.repo.save(miembro);
    return miembro;
  }

  async findAll() {
    return await this.repo.find();
  }

  async findByDni(dni: string) {
    return await this.repo.findOne({ where: { dni } });
  }

  async findOnePlain(id: number) {
    const miembro = await this.findOne(id);
    const { distrito, ...rest } = miembro;
    return { ...rest, distrito: distrito?.nombre };
  }

  async findOne(id: number) {
    const miembro = await this.repo.findOne({ where: { id } });
    if (!miembro) throw new NotFoundException(`Miembro #${id} no encontrado`);
    return miembro;
  }

  async update(id: number, updateMiembroDto: UpdateMiembroDto) {
    return `This action updates a #${id} miembro`;
  }

  async remove(id: number) {
    await this.findOne(id);
    await this.repo.delete(id);
    return;
  }
}
