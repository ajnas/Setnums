
package com.ajnas.Setnums;





import com.ajnas.framework.Screen;
import com.ajnas.framework.implementation.AndroidGame;

public class Main extends AndroidGame {

    
    boolean firstTimeCreate = true;

    @Override
    public Screen getInitScreen() {

        if (firstTimeCreate) {
            
            firstTimeCreate = false;
        }

        
       

        return new SplashLoadingScreen(this);

    }

    @Override
    public void onBackPressed() {
        getCurrentScreen().backButton();
    }

   

    @Override
    public void onResume() {
        super.onResume();

        

    }

    @Override
    public void onPause() {
        super.onPause();
        

    }
}
 