import { StyleSheet, Text, View, Image, Pressable, ScrollView } from "react-native";
import React,{useContext,useEffect,useState} from "react";

import { NewsContext } from "../utilities/NewsContext";

const Detail = (props) => {
  const {navigation} = props;
  const {id} = props?.route?.params;
  const {getDetail} = useContext(NewsContext);
  const [data, setData] = useState(null);


  useEffect(() => {
    const fetchData = async () =>{
        const result = await getDetail(id);
        setData(result);
    }
      id && fetchData();
    return () => {
      
    }
  }, [id])
  
  return (
    (data && id && data._id.toString() == id.toString() ) ?
    <ScrollView style={detailStyles.container}>
      <View style={detailStyles.authorInformation}>
        <Image source={require("../../../../../src/media/images/author.png")} />
        <View style={detailStyles.author}>
          <Text style={detailStyles.authorName}>BBC News</Text>
          <Text style={detailStyles.authorTime}>14m ago</Text>
        </View>
        <Pressable style={detailStyles.btnContainer}>
          <Text style={detailStyles.btnLabel}>Following</Text>
        </Pressable>
      </View>
      <Image
        style={detailStyles.newsImg}
        resizeMode="cover"
        source={{uri: data.image}}
      />
      <Text style={detailStyles.europe}>Europe</Text>
      <Text style={detailStyles.title}>{data.title}</Text>
      <Text style={detailStyles.content}>
        {data.content}
      </Text>
    </ScrollView>
    : <Text style = {{fontSize: 20, color:'black'}}>Loadding...</Text>
  );
};

export default Detail;

const detailStyles = StyleSheet.create({
  content: {
    marginTop: 16,
    fontWeight: "400",
    fontSize: 16,
    lineHeight: 24,
    letterSpacing: 0.12,
    color: "#4E4B66",
  },
    title: {
    marginTop: 4,
    fontWeight: "400",
    fontSize: 24,
    lineHeight: 36,
    letterSpacing: 0.12,
    color: "#000",
  },
  europe: {
    marginTop: 16,
    fontWeight: "400",
    fontSize: 14,
    lineHeight: 21,
    letterSpacing: 0.12,
    color: "#4E4B66",
  },

  newsImg: {
    width: "100%",
    height: 248,
    marginTop: 16,
    borderRadius: 6,
  },

  btnLabel: {
    fontWeight: "600",
    fontSize: 16,
    lineHeight: 24,
    letterSpacing: 0.12,
    color: "#fff",
  },
  btnContainer: {
    position: "absolute",
    right: 0,
    borderRadius: 6,
    paddingHorizontal: 11,
    paddingVertical: 5,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#1877F2",
  },
  authorTime: {
    fontWeight: "400",
    fontSize: 14,
    lineHeight: 21,
    letterSpacing: 0.12,
    color: "#4E4B66",
  },
  authorName: {
    fontWeight: "600",
    fontSize: 16,
    lineHeight: 24,
    letterSpacing: 0.12,
    color: "#000",
  },
  author: {
    marginLeft: 4,
  },
  authorInformation: {
    flexDirection: "row", //nằm trên 1 hàng
    alignItems: "center", // nằm giữa theo chiều dọc
  },

  container: {
    //flex: 1,
    backgroundColor: "#fff",
    padding: 24,
  },
});
