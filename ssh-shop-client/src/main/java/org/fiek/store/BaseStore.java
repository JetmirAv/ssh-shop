package org.fiek.store;

import eu.lestard.fluxfx.Store;
import org.fiek.store.auth.*;
import org.reactfx.EventSource;
import org.reactfx.EventStream;

import javax.inject.Singleton;

@Singleton
public class BaseStore extends Store {

    private final EventSource<AuthStore> authStoreEventSource = new EventSource<>();
    private final AuthStore authStore;

    public EventStream<AuthStore> getAuthStoreEventStream() {
        return authStoreEventSource;
    }

    public AuthStore getAuthStore() {
        return authStore;
    }

    public BaseStore() {
        authStore = new AuthStore();
        authStoreEventSource.push(authStore);
        subscribe(AddTokenAction.class, this::addTokenAction);
        subscribe(EditUserAction.class, this::editUserAction);
        subscribe(AddAddressAction.class, this::addAddressAction);
        subscribe(CityAction.class, this::GetCityAction);
        subscribe(CountryAction.class, this::GetCountryAction);
        subscribe(GetCountryByCityAction.class, this::GetCountryByCity);
    }

    private void editUserAction(EditUserAction t) {
        authStore.editUserAction(t.getUser());
        authStoreEventSource.push(authStore);
    }

    private void addTokenAction(AddTokenAction action) {
        authStore.addTokenAction(action.getToken(), action.getUser());
        authStoreEventSource.push(authStore);
    }

    private void addAddressAction(AddAddressAction t) {
        authStore.addAddressAction(t.getAddress());
        authStoreEventSource.push(authStore);
    }

    private void GetCityAction(CityAction t) {
        System.out.println("Ne basStore:" + t.getCities());
        authStore.GetCityAction(t.getCities());
        authStoreEventSource.push(authStore);
    }


    private void GetCountryAction(CountryAction t) {
        authStore.GetCountryAction(t.getCountries());
        authStoreEventSource.push(authStore);
    }

    private void GetCountryByCity(GetCountryByCityAction t) {
        authStore.GetCountryByCity(t.getCountry());
        authStoreEventSource.push(authStore);
    }

}
