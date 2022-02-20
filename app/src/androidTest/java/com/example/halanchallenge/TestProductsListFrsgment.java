package com.example.halanchallenge;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.testing.TestNavHostController;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.halanchallenge.model.ListActivityDataItem;
import com.example.halanchallenge.ui.fragments.ProductListFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class TestProductsListFrsgment {

    @IdRes
    private final int theme = R.style.Theme_AppCompat_DayNight_NoActionBar;

    private final DataFactory dataFactory = new DataFactory();

    @Before
    public void setup() {
        Bundle bundle = new Bundle();
        ListActivityDataItem listActivityDataItem = dataFactory.createItem();
        bundle.putParcelable("RESPONSE", listActivityDataItem.getLoginResponse());
        bundle.putParcelable("PRODUCTS", listActivityDataItem.getProductsList());
        TestNavHostController navController = new TestNavHostController(ApplicationProvider.getApplicationContext());
        FragmentScenario.launchInContainer(ProductListFragment.class, bundle, theme, Lifecycle.State.STARTED).onFragment(
                productListFragment ->{
                        assertNotNull(productListFragment.getActivity());
        productListFragment.getActivity().runOnUiThread(() -> navController.setGraph(R.navigation.nav_graph));
        Navigation.setViewNavController(productListFragment.requireView(), navController);
                navController.setCurrentDestination(R.id.productListFragment);}
        );
    }
    @Test
    public void testRecyclerItemClick() {
             onView(withId(R.id.products_list_rv)).perform(RecyclerViewActions.scrollToPosition(0)).perform(click());


    }
}
