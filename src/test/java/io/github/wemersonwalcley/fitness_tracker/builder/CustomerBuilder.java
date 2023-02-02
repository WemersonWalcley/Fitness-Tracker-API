package io.github.wemersonwalcley.fitness_tracker.builder;

import io.github.wemersonwalcley.fitness_tracker.model.CustomerModel;

public class CustomerBuilder {

    public static CustomerModel createObject(){
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(1L);
        customerModel.setAccountModel(AccountBuilder.createObject());
        customerModel.setListaTreino("Treino de musculação");

        return customerModel;
    }
}
