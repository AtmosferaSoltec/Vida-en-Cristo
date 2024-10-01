import { Miembro } from 'src/modules/miembro/entities/miembro.entity';
import { Column, Entity, OneToMany, PrimaryGeneratedColumn } from 'typeorm';

@Entity({ name: 'distrito' })
export class Distrito {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({
    type: 'varchar',
    length: 50,
    nullable: false,
    unique: true,
  })
  nombre: string;

  @OneToMany(() => Miembro, (miembro) => miembro.distrito)
  miembros: Miembro[];
}
