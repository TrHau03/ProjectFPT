import { StyleSheet, Text, View,Image,TextInput,FlatList,Pressable } from 'react-native'
import React from 'react'

const Home = (props) => {
    const { navigation} = props;
    const renderItem = ({item}) => {
        const {title, image} = item;

        return (
            <Pressable>
                <Image source = {require('./media/images/bg.png')}/>
                <Text style = {mystyles.text}>{title}</Text>
            </Pressable>
            
          );
        };
  return (
    <View style = {mystyles.container}>
      <Text style = {mystyles.textSearch}>Search</Text>
      <View style = {mystyles.viewSearch}>
        <Image style = {mystyles.imagesearch} source = {require('./media/images/search.png')}/>
        <TextInput>
            <Text style = {mystyles.textinput}>Search on foodly</Text>
        </TextInput>
      </View>
      <Text style = {mystyles.textRes}>Top Restaurants</Text>
      <FlatList
      style = {mystyles.flat}
        data={data}
        renderItem={renderItem}
        keyExtractor={item => item._id}
        showsVerticalScrollIndicator={false}
        number={2} // ẩn thanh cuộn
      />
    </View>
  )
}

export default Home

const mystyles = StyleSheet.create({
    flat:{
        flexDirection: 'row',
    },
    text:{
        color: '#010F07',
        fontSize: 16,
    },
    textRes: {
        color: '#000000',
        fontSize: 16, lineHeight: 24,
        fontStyle: 'regular',
        marginTop: 34,
    },
    textinput:{
        fontSize: 16,
        lineHeight: 24,
        letterSpacing: -0.4,
        fontStyle: 'regular',
        color: '#868686',
    },
    viewSearch:{
        marginTop: 20,
        flexDirection: 'row',
        borderRadius: 8,
        backgroundColor: '#FBFBFB',
    },
    imagesearch:{
        position: 'relative',
            top: 8,
        width: 30,
        height: 30,
    },
    textSearch:{
        color: '#010F07',
        fontSize: 28,
        lineHeight: 36,
        letterSpacing: 0.18,
        fontStyle: 'Semibold',
    },
    container:{
        padding: 20,
    }
})
var data = [
      {
        title: "The Halal Guys",
       
      },
      {
        title: "The Halal Guys",
       
      },




      {
        title: "The Halal Guys",
       
      },
      {
        title: "The Halal Guys",
       
      },
      {
        title: "The Halal Guys",
       
      },
      {
        title: "The Halal Guys",
       
      },
      {
        title: "The Halal Guys",
       
      },
      {
        title: "The Halal Guys",
       
      },
      {
        title: "The Halal Guys",
       
      },
]