package ru.karapetiandav.yamblzproject.ui.presenters;


import org.junit.Test;

import ru.karapetiandav.yamblzproject.TestDataDependencies;

import static org.assertj.core.api.Assertions.assertThat;

public class AddCityPresenterCacheTest {

    private TestDataDependencies deps;

    @Test
    public void testCommon() {
        deps = new TestDataDependencies();
        AddCityPresenterCache cache = new AddCityPresenterCache();
        assertThat(cache.isCacheExist()).isFalse();
        cache.updateData(deps.getCityViewModelList());
        assertThat(cache.isCacheExist()).isTrue();
        assertThat(cache.getCities()).isEqualTo(deps.getCityViewModelList());
        String lastText = "text";
        cache.setLastText(lastText);
        assertThat(cache.getLastText()).isEqualTo(lastText);
    }
}
