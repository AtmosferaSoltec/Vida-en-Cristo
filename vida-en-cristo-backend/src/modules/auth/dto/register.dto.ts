import { IsString, IsOptional, IsNotEmpty, MinLength, MaxLength, IsNumber, IsPositive } from "class-validator";

export class RegisterDto {
    @IsString({ message: 'El nombre debe ser un texto' })
    @IsOptional()
    nombre?: string;
  
    @IsString({ message: 'El usuario debe ser un texto' })
    @IsNotEmpty({ message: 'El usuario es requerido' })
    usuario: string;
  
    @IsString({ message: 'La clave debe ser un texto' })
    @IsNotEmpty({ message: 'La clave es requerida' })
    @MinLength(4, { message: 'La clave debe tener al menos 4 caracteres' })
    @MaxLength(16, { message: 'La clave debe tener como máximo 16 caracteres' })
    clave: string;
  
    @IsNumber({}, { message: 'El tipo de usuario debe ser un número' })
    @IsPositive({ message: 'El tipo de usuario debe ser un número positivo' })
    @IsNotEmpty({ message: 'El tipo de usuario es requerido' })
    tipoUsuario: number;
  }