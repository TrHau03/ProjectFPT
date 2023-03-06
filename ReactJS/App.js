/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React from 'react';
import {useContext} from 'react'
// import {UserProvider} from './src/components/app/user/utilities/UserContext'
// import {NewsProvider} from './src/components/app/news/utilities/NewsContext'
// import AppNavigation from './src/components/app/appNavigations/AppNavigation'
import Login from './src/Login';
import Home from './src/Home';
import Product from './src/Product';
/*import {createNativeStackNavigator} from '@react-navigation/native-stack';
import {NavigationContainer} from '@react-navigation/native';*/
const App: () => Node = () => {
  //const Stack = createNativeStackNavigator();
  return (
    <Product/>
    //     <UserProvider>
    //       <NewsProvider>
    //         <AppNavigation/>
    //       </NewsProvider>
    // </UserProvider>
    /*<NavigationContainer>
      <Stack.Navigator screenOptions={{headerShown: false}}>
        <Stack.Screen name="Login" component={Login} />
        <Stack.Screen name="Home" component={Home} />
      </Stack.Navigator>
    </NavigationContainer>*/



  );
};

export default App;
