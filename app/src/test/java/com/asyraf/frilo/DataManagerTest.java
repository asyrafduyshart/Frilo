package com.asyraf.frilo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import com.asyraf.frilo.common.TestDataFactory;
import com.asyraf.frilo.data.DataManager;
import com.asyraf.frilo.data.model.NamedResource;
import com.asyraf.frilo.data.model.Pokemon;
import com.asyraf.frilo.data.model.PokemonListResponse;
import com.asyraf.frilo.data.remote.MvpStarterService;
import com.asyraf.frilo.util.RxSchedulersOverrideRule;
import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();
    @Mock
    MvpStarterService mMockMvpStarterService;
    private DataManager mDataManager;

    @Before
    public void setUp() {
        mDataManager = new DataManager(mMockMvpStarterService);
    }

    @Test
    public void getPokemonListCompletesAndEmitsPokemonList() {
        List<NamedResource> namedResourceList = TestDataFactory.makeNamedResourceList(5);
        PokemonListResponse pokemonListResponse =
                new PokemonListResponse();
        pokemonListResponse.results = namedResourceList;

        when(mMockMvpStarterService.getPokemonList(anyInt()))
                .thenReturn(Single.just(pokemonListResponse));

        mDataManager.getPokemonList(10)
                .test()
                .assertComplete()
                .assertValue(TestDataFactory.makePokemonNameList(namedResourceList));
    }

    @Test
    public void getPokemonCompletesAndEmitsPokemon() {
        String name = "charmander";
        Pokemon pokemon = TestDataFactory.makePokemon(name);
        when(mMockMvpStarterService.getPokemon(anyString()))
                .thenReturn(Single.just(pokemon));

        mDataManager.getPokemon(name)
                .test()
                .assertComplete()
                .assertValue(pokemon);
    }
}
