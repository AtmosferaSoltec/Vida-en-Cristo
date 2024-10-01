import { Distrito } from 'src/modules/distrito/entities/distrito.entity';
import { Column, Entity, ManyToOne, PrimaryGeneratedColumn } from 'typeorm';
import { Familia } from '../../familia/entities/familia.entity';

@Entity({ name: 'miembro' })
export class Miembro {
  @PrimaryGeneratedColumn()
  id: number;

  @Column({
    type: 'varchar',
    length: 15,
    nullable: false,
    unique: true,
  })
  dni: string;

  @Column({
    type: 'varchar',
    length: 50,
    nullable: true
  })
  nombres?: string;

  @Column({
    type: 'varchar',
    length: 50,
    nullable: true
  })
  apellidos?: string;

  @Column({
    type: 'varchar',
    length: 50,
    nullable: true
  })
  celular?: string;

  @Column({
    type: 'varchar',
    length: 50,
    nullable: true
  })
  direc?: string;

  @Column({
    type: 'date',
    nullable: true
  })
  fecha_nac?: Date;

  @Column({
    type: 'timestamp',
    default: () => 'CURRENT_TIMESTAMP',
  })
  created_at: Date;

  @Column({
    type: 'timestamp',
    default: () => 'CURRENT_TIMESTAMP',
    onUpdate: 'CURRENT_TIMESTAMP',
  })
  updated_at: Date;

  @ManyToOne(() => Distrito, (distrito) => distrito.miembros, {
    eager: true,
    nullable: true,
    cascade: true,
  })
  distrito: Distrito;

  @ManyToOne(
    () => Familia,
    f => f.miembros
  )
  familia: Familia;
}
