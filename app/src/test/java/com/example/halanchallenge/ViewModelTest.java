package com.example.halanchallenge;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import com.example.halanchallenge.model.ListActivityDataItem;
import com.example.halanchallenge.model.LoginResponse;
import com.example.halanchallenge.model.Profile;
import com.example.halanchallenge.ui.viewmodel.MainViewModel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;


public class ViewModelTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    public MainViewModel viewModel= mock(MainViewModel.class);

    @Rule
    public RxImmediateSchedulerRule rxImmediateSchedulerRule = new RxImmediateSchedulerRule();
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getStuff() {
        viewModel.login("username", "password");
        when(viewModel.getResult()).thenAnswer(createAnswer(new MutableLiveData<>(new ListActivityDataItem(new LoginResponse("Ok","",new Profile("","","","","")),null))));
        Assert.assertEquals(new ListActivityDataItem(new LoginResponse("Ok","",new Profile("","","","","")),null).getLoginResponse().getStatus(),viewModel.getResult().getValue().getLoginResponse().getStatus());

    }

    public static <T> Answer<T> createAnswer(final T value) {
        Answer<T> dummy = invocation -> value;
        return dummy;
    }
}
