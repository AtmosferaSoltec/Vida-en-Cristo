import { Column, Entity, OneToMany, PrimaryGeneratedColumn } from "typeorm";
import { Miembro } from "../../miembro/entities/miembro.entity";

@Entity({name: 'familia'})
export class Familia {

    @PrimaryGeneratedColumn()
    id: number;

    @Column({
        type: 'varchar',
        length: 125,
        nullable: false,
        unique: true,
    })
    nombre: string;

    @OneToMany(
        ()=>Miembro,
        m => m.familia
    )
    miembros: Miembro[];
}