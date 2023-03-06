import React from 'react'
import { NavigationContainer } from '@react-navigation/native'
import Login from '../screens/Login';
import Register from '../screens/Register';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
const Stack = createNativeStackNavigator();
const UserNavigation = () => {
  return (
        <Stack.Navigator screenOptions={{headerShown: false}}>
            <Stack.Screen name="Login" component={Login}/>
            <Stack.Screen name="Register" component={Register}/>
        </Stack.Navigator>
  )
}

export default UserNavigation