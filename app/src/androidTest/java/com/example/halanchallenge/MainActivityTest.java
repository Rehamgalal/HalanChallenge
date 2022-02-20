package com.example.halanchallenge;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.testing.TestNavHostController;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.halanchallenge.model.ListActivityDataItem;
import com.example.halanchallenge.ui.fragments.LoginFragment;
import com.example.halanchallenge.ui.fragments.ProductListFragment;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @IdRes
    private final int theme = R.style.Theme_AppCompat_DayNight_NoActionBar;

    private final DataFactory dataFactory = new DataFactory();
    @Test
    public void testNavGraph() {
        Bundle args = new Bundle();
        FragmentScenario<LoginFragment> navhostScenario = FragmentScenario.launchInContainer(LoginFragment.class, args, theme, Lifecycle.State.STARTED);

        navhostScenario.onFragment(fragment -> {
            // Create a NavController and set the NavController property on the fragment
            assertNotNull(fragment.getActivity());
            TestNavHostController navController = new TestNavHostController(fragment.getActivity());
            fragment.getActivity().runOnUiThread(() -> navController.setGraph(R.navigation.nav_graph));
            Navigation.setViewNavController(fragment.requireView(), navController);

            // Then navigate
            Bundle bundle = new Bundle();
            ListActivityDataItem listActivityDataItem = dataFactory.createItem();
            bundle.putParcelable("RESPONSE", listActivityDataItem.getLoginResponse());
            bundle.putParcelable("PRODUCTS", listActivityDataItem.getProductsList());
            navController.navigate(R.id.action_loginFragment_to_productListFragment,bundle);
            NavDestination destination = navController.getCurrentDestination();
            assertNotNull(destination);
            assertEquals(destination.getId(), R.id.productListFragment);
            navController.navigate(R.id.action_productListFragment_to_productDetailsFragment);
            NavDestination destinationDetails = navController.getCurrentDestination();
            assertNotNull(destinationDetails);
            assertEquals(destinationDetails.getId(), R.id.productDetailsFragment);
        });
    }
}
