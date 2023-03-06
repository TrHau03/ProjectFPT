import { StyleSheet, Text, View, Image, TextInput,Pressable , Alert} from 'react-native'
import React , {useContext, useState}from 'react'

const Product = () => {
  const [email, setemail] = useState('');
  const [pass, setpass] = useState('');
  const [taikhoan, settaikhoan] = useState('');

  const check = () =>{
    if(taikhoan == "" || pass == ""|| email == ""){
      Alert.alert('K đc để trống');
    }else{
      if (taikhoan == 'fpoly' && pass == '123' && email == 'hoang@123') {
        Alert.alert('Thành công');
      }
    }
  }
  return (
    <View style={styles.container}> 
      <Image style = {styles.img} source={require('./media/images/anh1.png')}/>
      <View style = {styles.content}>
        <Text style={styles.titleContainer}>Lets Get Started</Text>
        <Text style={styles.title}>Create An Account</Text>

        <View style={styles.viewInput}>
          <TextInput
            style={styles.textInput}
            value={taikhoan}
            onChangeText={settaikhoan}
          />
          <Image
            style={styles.eye}
            source={require('./media/images/Taikhoan.png')}
          />
        </View>

        <View style={styles.viewInput}>
          <TextInput
            style={styles.textInput}
            value={email}
            onChangeText={setemail}
          />
          <Image
            style={styles.eye}
            source={require('./media/images/Email.png')}
          />
        </View>

        <View style={styles.viewInput}>
          <TextInput
            style={styles.textInput}
            value={pass}
            onChangeText={setpass}
          />
          <Image
            style={styles.eye}
            source={require('./media/images/Pass.png')}
          />
        </View>

      </View>
      <Pressable style={styles.btn} 
      onPress={check}
      >
        <Text style={styles.textBtn}>Create Account</Text>
      </Pressable>
    </View>
  )
}

export default Product

const styles = StyleSheet.create({
  textBtn:{
    fontSize: 18,
    color: 'white',
  },
  btn:{
    position: 'absolute',
    top: 700,
    justifyContent: 'center',
    alignItems: 'center',
    marginLeft: 80,
    width: 228,
    borderRadius: 20,
    height: 50,
    backgroundColor: '#FF8B6A',
  },
  eye: {
    position: 'relative',
    left: 5,
    top: -40,
    width: 25,
  },
  textInput: {
    backgroundColor: '#FFFFFF',
    borderWidth: 1,
    borderRadius: 2,
  },

  title:{
    color: '#000',
    marginTop: 14,
    fontSize: 22,
    fontWeight:'500',
    lineHeight: 27,
  },
  titleContainer:{
    color: '#000',
    marginLeft: 80,
    alignItems: 'center',
    justifyContent: 'center',
    fontWeight: '700',
    fontSize: 24,
    lineHeight: 29,
  },
  content:{
    width: '100%',
    padding: 28
  },
  img:{
    width: '100%',
    height: 379
  },
  container:{
    flex: 1,
    backgroundColor: 'red'
  }
})