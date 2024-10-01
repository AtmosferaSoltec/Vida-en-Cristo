import {
  Controller,
  Get,
  Post,
  Body,
  Patch,
  Param,
  Delete,
} from '@nestjs/common';
import { MiembroService } from './miembro.service';
import { CreateMiembroDto } from './dto/create-miembro.dto';
import { UpdateMiembroDto } from './dto/update-miembro.dto';

@Controller('miembro')
export class MiembroController {
  constructor(private readonly miembroService: MiembroService) {}

  @Post()
  create(@Body() createMiembroDto: CreateMiembroDto) {
    return this.miembroService.create(createMiembroDto);
  }

  @Get()
  async findAll() {
    return (await this.miembroService.findAll()).map((miembro) => {
      const { distrito, ...rest } = miembro;
      return { ...rest, distrito: distrito?.nombre };
    });
  }

  @Get(':id')
  findOne(@Param('id') id: string) {
    return this.miembroService.findOnePlain(+id);
  }

  @Patch(':id')
  update(@Param('id') id: string, @Body() updateMiembroDto: UpdateMiembroDto) {
    return this.miembroService.update(+id, updateMiembroDto);
  }

  @Delete(':id')
  remove(@Param('id') id: string) {
    return this.miembroService.remove(+id);
  }
}
