import { Type } from 'class-transformer';
import {
  IsDate,
  IsNotEmpty,
  IsNumber,
  IsOptional,
  IsPositive,
  IsString,
} from 'class-validator';

export class CreateMiembroDto {
  @IsString({ message: 'El dni debe ser un texto' })
  @IsNotEmpty({ message: 'El dni es requerido' })
  dni: string;

  @IsString({ message: 'El nombre debe ser un texto' })
  @IsOptional()
  nombres: string;

  @IsString({ message: 'El apellido debe ser un texto' })
  @IsOptional()
  apellidos: string;

  @IsString({ message: 'El celular debe ser un texto' })
  @IsOptional()
  celular: string;

  @IsString({ message: 'La dirección debe ser un texto' })
  @IsOptional()
  direc: string;

  @IsOptional()
  @Type(() => Date)
  @IsDate({ message: 'La fecha de nacimiento debe ser una fecha' })
  fecha_nac: Date;

  @IsNumber({}, { message: 'El id del distrito debe ser un número' })
  @IsPositive({ message: 'El id del distrito debe ser un número positivo' })
  @IsOptional()
  id_distrito: number;
}
