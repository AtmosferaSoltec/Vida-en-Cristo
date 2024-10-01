import { PrimaryGeneratedColumn, Column, Entity, ManyToOne } from "typeorm"
import { TipoUsuario } from "./tipo-usuario.entity"

@Entity('usuario')
export class Usuario {


    @PrimaryGeneratedColumn()
    id: number

    @Column({
        type: 'varchar',
        length: 50,
        nullable: false,
        unique: true
    })
    usuario: string

    @Column({
        type: 'varchar',
        length: 200,
        nullable: false
    })
    clave: string

    @Column({
        type: 'varchar',
        length: 100,
        nullable: true
    })
    nombre?: string

    @Column({
        type: 'timestamp',
        default: () => 'CURRENT_TIMESTAMP'
    })
    created_at: Date

    @ManyToOne(
        () => TipoUsuario,
        tipoUsuario => tipoUsuario.usuarios,
        {
            eager: true
        }
    )
    tipoUsuario: TipoUsuario

}
