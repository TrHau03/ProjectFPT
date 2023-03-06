import {
  StyleSheet,
  Text,
  View,
  TextInput,
  Button,
  TouchableOpacity,
  Pressable,
  Image,
  Alert,
  ScrollView,
} from "react-native";
import React, {useContext, useState} from "react";
import { UserContext } from "../utilities/UserContext";


const Login = (props) => {
  const { navigation} = props;
  const {login} = useContext(UserContext);
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  
  
  const handleLogin = async () => {

    if(!email || !password) {
      Alert.alert("Vui lòng nhập email và password")
    }
    const result = await login(email, password);
    console.log(result);
  }
  function MyCheckbox() {
    const [checked, setChecked] = useState(false);
    return (
      <Pressable
        style={[myStyles.checkboxBase, checked && myStyles.checkboxChecked]}
        onPress={() => setChecked(!checked)}>
        {checked && (
          <Image
            style={myStyles.checkbox}
            source={require('../../../../media/images/icons8-done-12.png')}
          />
        )}
      </Pressable>
    );
  }
  return (
    <ScrollView>
    <View style={myStyles.container}>
      <Text style={myStyles.hello}>Hello</Text>
      <Text style={myStyles.again}>Again!</Text>
      <Text style={myStyles.welcome}>Welcome back you've been missed</Text>
      <View style={myStyles.inputContainer}>
        <Text style={myStyles.label}>Username*</Text>
        <TextInput style={myStyles.input}
          value={email}
          onChangeText = {setEmail}
        />
      </View>

      <View style={myStyles.inputContainer}>
        <Text style={myStyles.label}>Password*</Text>
        <TextInput style={myStyles.input} 
          secureTextEntry
 
          onChangeText ={setPassword}
          />
        <Image
          style={myStyles.eyeIcon}
          source={require("../../../../../src/media/images/eye.png")}
        />
      </View>
      <View style={myStyles.forgetPassword}>
        <MyCheckbox />
        <Text style={myStyles.remember}>Remember me</Text>
        <Text style={myStyles.forget}>Forgot the password ?</Text>
      </View>
      <Pressable style={myStyles.btnContainer} onPress={handleLogin}>
        <Text style={myStyles.btnLabel}>Login</Text>
      </Pressable>

      <View style={myStyles.continue}>
        <Text style={myStyles.continueLabel}>or continue with</Text>
      </View>

      <View style={myStyles.socialButtonContainer}>
          <Pressable style={myStyles.btnFBGG}>
            <Image
              style={myStyles.fbIcon}
              source={require("../../../../../src/media/images/facebook.png")}
            />
            <Text style={myStyles.btnFBGGLabel}>Facebook</Text>
          </Pressable>

          <Pressable style={myStyles.btnFBGG}>
            <Image
              style={myStyles.ggIcon}
              source={require("../../../../../src/media/images/google.png")}
            />
            <Text style={myStyles.btnFBGGLabel}>Google</Text>
          </Pressable>
      </View>
      <View style={myStyles.account}>
        <Text style={myStyles.accountLabel}>don’t have an account ? </Text>
        <Pressable 
          onPress={() => navigation.navigate('Register')}
        >
          <Text style={myStyles.signUpLabel}>Register</Text>
        </Pressable>
      </View>
    </View>
    </ScrollView>
  );
};

export default Login;

const myStyles = StyleSheet.create({
  forgetPassword: {
    flexDirection: 'row',
  },
  forget: {
    marginTop: 9.5,
    fontSize: 14,
    lineHeight: 21,
    letterSpacing: 0.12,
    color: '#5890FF',
    position: 'absolute',
    right: 0,
  },
  remember: {
    marginTop: 9.5,
    marginLeft: 6,
    fontSize: 14,
    lineHeight: 21,
    letterSpacing: 0.12,
    color: '#4E4B66',
  },
  checkbox: {
    width: 16,
    height: 16,
  },
  checkboxBase: {
    marginTop: 10,
    width: 20,
    height: 20,
    justifyContent: 'center',
    alignItems: 'center',
    borderRadius: 4,
    borderWidth: 2,
    borderColor: '#4E4B66',
    backgroundColor: 'transparent',
  },
  checkboxChecked: {
    backgroundColor: '#1877F2',
  },
  signUpLabel: {
    fontWeight: '400',
    fontSize: 14,
    lineHeight: 21,
    color: '#1877f2'
  },
  accountLabel: {
    fontWeight: '400',
    fontSize: 14,
    lineHeight: 21,
    color: '#667080'
  },
  account:{
    flexDirection: 'row',
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: 16
  },
  btnFBGGLabel:{
    fontWeight: '600',
    fontSize: 16,
    lineHeight: 24,
    color: '#667080',
    letterSpacing: 0.12,
    marginLeft: 10
  },
  btnFBGG:{
    flexDirection: "row",
    paddingHorizontal: 24,
    paddingVertical: 12,
    height: 48,
    width: '47%',
    backgroundColor: '#EEF1F4',
    borderRadius: 6,
    alignItems: "center",
    justifyContent: "center",
  },

  socialButtonContainer: {
    flexDirection: "row",
    justifyContent: "space-between",
    marginTop: 16,
  },

  continueLabel: {
    fontWeight: "400",
    fontSize: 14,
    lineHeight: 21,
    letterSpacing: 0.12,
    color: "#4E4B66",
  },

  continue: {
    alignItems: "center",
    justifyContent: "center",
    marginTop: 16,
  },

  eyeIcon: {
    position: "absolute",
    top: 37,
    right: 10,
  },

  inputContainer: {
    marginBottom: 16,
    position: "relative",
  },

  btnLabel: {
    fontWeight: "600",
    fontSize: 16,
    lineHeight: 24,
    letterSpacing: 0.12,
    color: "#fff",
  },

  btnContainer: {
    justifyContent: "center",
    alignItems: "center",
    marginTop: 16,
    paddingVertical: 13,
    paddingHorizontal: 24,
    height: 50,
    width: "100%",
    backgroundColor: "#1877f2",
    borderRadius: 6,
  },

  input: {
    width: "100%",
    padding: 10,
    height: 48,
    backgroundColor: "#fff",
    borderRadius: 6,
    borderWidth: 1,
    borderColor: "#4e4b66",
  },
  label: {
    fontWeight: "400",
    fontSize: 14,
    lineHeight: 21,
    letterSpacing: 0.12,
    color: "#4e4b66",
    marginBottom: 4,
  },
  container: {
    flex: 1,
    backgroundColor: "#fff",
    padding: 24,
  },
  hello: {
    fontWeight: "700",
    fontSize: 48,
    lineHeight: 72,
    letterSpacing: 0.12,
    color: "#1877f2",
  },
  again: {
    fontWeight: "700",
    fontSize: 48,
    lineHeight: 72,
    letterSpacing: 0.12,
    color: "#050505",
  },
  welcome: {
    width: 222,
    fontWeight: "400",
    fontSize: 20,
    lineHeight: 30,
    letterSpacing: 0.12,
    color: "4e4b66",
    marginBottom: 48,
  },
});
