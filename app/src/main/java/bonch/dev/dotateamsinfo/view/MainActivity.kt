package bonch.dev.dotateamsinfo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import bonch.dev.dotateamsinfo.R

class MainActivity : AppCompatActivity() {

    private lateinit var fragmentContainer:FrameLayout
    private lateinit var fragmentManager:FragmentManager
    private lateinit var fragmentTransaction:FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        startFragment(TeamsListFragment())

    }

    private fun startFragment(fragment:Fragment) {
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

    }

    private fun initView() {
        fragmentContainer = findViewById(R.id.fragment_container)

        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
    }

}
