import {
  StyleSheet,
  Text,
  View,
  TextInput,
  Image,
  Pressable,
} from 'react-native';
import React from 'react';
import  {useContext,useState} from 'react'

const Login = props => {
  const {navigation} = props;
  const [email, setemail] = useState('');
  const [pass, setPass] = useState('');
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const check = () =>{
    if(email ==='fpoly' && password === '123'){
        isLoggedIn = true;
    }else{
        isLoggedIn = false;
    }
  }
  return (
    <View style={mystyles.container}>
      <Text style={mystyles.textWel}>Welcome to</Text>
      <Text
        style={
          mystyles.textEnter
        }>{`Enter your Phone number or Email address for sign in. Enjoy your food :)`}</Text>
      <View style={mystyles.viewInput}>
        <Text style={mystyles.textEmail}>EMAIL ADDRESS</Text>
        <TextInput
          style={mystyles.textInput}
          value={email}
          onChangeText={setemail}
        />
        <Image
          style={mystyles.check}
          source={require('./media/images/check.png')}
        />
      </View>
      <View style={mystyles.viewInput}>
        <Text style={mystyles.textEmail}>PASSWORD</Text>
        <TextInput
          style={mystyles.textInput}
          value={pass}
          onChangeText={setPass}
        />
        <Image
          style={mystyles.eye}
          source={require('./media/images/invisible.png')}
        />
      </View>
      <Text style={mystyles.forgetpass}>Forget Password ?</Text>
      <Pressable
        style={mystyles.button}
        onPress={() => navigation.navigate('Home')}>
        <Text style={mystyles.btnText}>Sign in</Text>
      </Pressable>
    </View>
  );
};

export default Login;

const mystyles = StyleSheet.create({
  btnText: {
    color: '#ffffff',
    fontStyle: 'bold',
    fontSize: 14,
    lineHeight: 24,
    letterSpacing: 0.8,
  },
  button: {
    backgroundColor: '#22A45D',
    alignItems: 'center',
    justifyContent: 'center',
    height: 48,
    width: '100%',
    borderRadius: 8,
    marginTop: 24,
  },
  forgetpass: {
    color: '#010F07',
    fontSize: 14,
    lineHeight: 20,
    letterSpacing: -0.24,
    alignItems: 'center',
    position: 'relative',
    left: '35%',
  },
  eye: {
    position: 'relative',
    right: '-95%',
    top: -40,
    width: 25,
  },
  check: {
    position: 'relative',
    right: '-95%',
    top: -25,
    width: 15,
    height: 15,
  },
  textInput: {
    borderBottomColor: 'grey',
    borderBottomWidth: 1,
  },
  viewInput: {},
  textEmail: {
    fontSize: 12,
    lineHeight: 20,
    letterSpacing: 0.8,
    color: '#868686',
    marginTop: 35,
  },
  textEnter: {
    fontStyle: 'Regular',
    fontSize: 16,
    letterSpacing: -0.4,
    color: '#868686',
    width: 274,
    lineHeight: 24,
    marginTop: 20,
  },
  textWel: {
    fontSize: 34,
    lineHeight: 42,
    letterSpacing: 0.22,
    color: '#010F07',
    fontStyle: 'Regular',
    marginTop: 50,
  },
  container: {
    padding: 20,
  },
});
