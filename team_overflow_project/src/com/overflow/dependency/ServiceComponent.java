package com.overflow.dependency;

import com.overflow.activity.*;
import dagger.Component;

import javax.inject.Singleton;

/**
 * This class manages service dependencies.
 */
@Singleton
@Component(modules = {DaoModule.class})
public interface ServiceComponent {

    CreateAProductActivity provideCreateAProductActivity();
    GetAProductActivity provideGetAProductActivity();
    UpdateAProductActivity provideUpdateAProductActivity();
    DeleteAProductActivity provideDeleteAProductActivity();
    GetAllProductsActivity provideGetAllProductsActivity();

    GetClientActivity provideGetClientActivity();
    UpdateClientActivity provideUpdateClientActivity();
    CreateClientActivity provideCreateClientActivity();

    GetClientInventoryActivity provideGetClientInventoryActivity();
    UpdateInventoryActivity provideUpdateInventoryActivity();
}
