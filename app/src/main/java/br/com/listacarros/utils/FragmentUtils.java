package br.com.listacarros.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentUtils {

    public static void showFragmentAndAddBackStack(FragmentTransaction tx, int container, Fragment fragment, String name){
          tx.replace(container, fragment)
                 .addToBackStack(name)
                 .commit();
    }

    public static void showFragment(FragmentTransaction tx, int container, Fragment fragment){
        tx.replace(container, fragment).commit();
    }
}
