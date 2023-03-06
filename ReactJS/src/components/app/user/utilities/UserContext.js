
import React, {useState, createContext} from 'react'
import AxiosInstance from '../../axiosClient/AxiosInstance';
import AsyncStorage from '@react-native-async-storage/async-storage';


export const UserContext = createContext();


export const UserProvider = (props) => {
    const {children} = props;
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const login = async (email, password) => {
        try {
            const response = await AxiosInstance().post('/auth/login',
            {
                email: email,
                password: password
            } );
            const token = response.data.token;
            await AsyncStorage.setItem('token', token);
            setIsLoggedIn(true);
            return true;
        } catch (error) {
            console.log('Login Error: ', error);
        }
        return false;
    }

    const register = async (email, password) => {
        try {
            const response = await AxiosInstance().post('/users/register',
            {
                email: email,
                password : password
            });
            return true;
        } catch (error) {
            console.log('Register Error: ', error);
        }
        return false;
    }
    
    return(
        <UserContext.Provider 
            value={{isLoggedIn, setIsLoggedIn, login, register}}>
            {children}
        </UserContext.Provider>
    )
}