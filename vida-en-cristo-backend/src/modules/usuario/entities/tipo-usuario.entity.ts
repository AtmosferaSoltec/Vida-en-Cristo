import { Column, Entity, OneToMany, PrimaryGeneratedColumn } from "typeorm";
import { Usuario } from "./usuario.entity";

@Entity('tipo_usuario')
export class TipoUsuario {

    @PrimaryGeneratedColumn()
    id: number

    @Column({
        type: 'varchar',
        length: 50,
        nullable: false,
        unique: true
    })
    nombre: string

    @OneToMany(
        () => Usuario,
        usuario => usuario.tipoUsuario
    )
    usuarios: Usuario[]
}
