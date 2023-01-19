package io.github.wemersonwalcley.fitness_tracker.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String listaTreino;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "tb_account_id")
    @MapsId
    private Account account;

}
