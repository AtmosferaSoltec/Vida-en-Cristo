import { IsNotEmpty, IsString } from "class-validator";

export class CreateDistritoDto {
    @IsString({ message: 'El nombre debe ser un texto' })
    @IsNotEmpty({ message: 'El nombre es requerido' })
    nombre: string;
}
