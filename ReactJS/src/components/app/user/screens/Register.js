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

  
  const Register = (props) => {
    const { navigation} = props;
    const {register} = useContext(UserContext);
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const handIsRegister = async () => {

      if(!email || !password) {
        Alert.alert("Vui lòng nhập email và password")
        return;
      }
      if(password != confirmPassword) {
        Alert.alert("Mật khẩu không khớp")
        return;
      }
      const result = await register(email, password);
      if(result){
        navigation.navigate('Login');
      }else{
        Alert.alert("Đăng ký không thành công")
      }
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
            onChangeText={setEmail}
            />
        </View>
  
        <View style={myStyles.inputContainer}>
          <Text style={myStyles.label}>Password*</Text>
          <TextInput style={myStyles.input}
           secureTextEntry            
          value={password}
          onChangeText={setPassword}/>
          <Image
            style={myStyles.eyeIcon}
            source={require("../../../../../src/media/images/eye.png")}
          />
        </View>

        <View style={myStyles.inputContainer}>
          <Text style={myStyles.label}>Confirm Password</Text>
          <TextInput style={myStyles.input}
           secureTextEntry            
          value={confirmPassword}
          onChangeText={setConfirmPassword}/>
          <Image
            style={myStyles.eyeIcon}
            source={require("../../../../../src/media/images/eye.png")}
          />
        </View>
  
  
        <Pressable style={myStyles.btnContainer} onPress={handIsRegister}>
          <Text style={myStyles.btnLabel}>Register</Text>
        </Pressable>
        <View style={myStyles.account}>
          <Text style={myStyles.accountLabel}>already having an account ? </Text>
          
          <Pressable
            onPress={() => navigation.goBack()}
          >
            <Text style={myStyles.signUpLabel}>Login</Text>
          </Pressable>

        </View>
      </View>
      </ScrollView>
    );
  };
  
  export default Register;
  
  const myStyles = StyleSheet.create({
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
  