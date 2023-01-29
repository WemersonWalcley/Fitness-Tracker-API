package io.github.wemersonwalcley.fitness_tracker.builder;

import io.github.wemersonwalcley.fitness_tracker.entity.CustomerEntity;

public class CustomerBuilder {

    public static CustomerEntity createObject(){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(1L);
        customerEntity.setAccountEntity(AccountBuilder.createObject());
        customerEntity.setListaTreino("Treino de musculação");

        return customerEntity;
    }
}
