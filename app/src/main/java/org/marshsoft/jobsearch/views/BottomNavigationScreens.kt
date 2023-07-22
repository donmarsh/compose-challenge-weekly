package org.marshsoft.jobsearch.views
import org.marshsoft.jobsearch.R

sealed class BottomNavigationScreens(var title:String, var icon: Int, var screenRoute:String) {
    object Home : BottomNavigationScreens("Home", R.drawable.outline_home_24,"home")
    object BookMark: BottomNavigationScreens("BookMark", R.drawable.majesticons_bookmark_minus_line,"bookmark")
    object Messages: BottomNavigationScreens("Messages", R.drawable.ant_design_message_outlined,"messages")
    object Profile: BottomNavigationScreens("Profile", R.drawable.typcn_user,"profile")
}