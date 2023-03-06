import {
  StyleSheet,
  Text,
  View,
  Image,
  TextInput,
  Pressable,
  Alert,
} from "react-native";
import React, { useCallback, useState, useContext } from "react";
import { NewsContext } from "../utilities/NewsContext";
import { launchCamera, launchImageLibrary } from "react-native-image-picker";

const Add = (props) => {
  const { navigation } = props;
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [image, setImage] = useState(null);
  const { uploadImage, saveNews } = useContext(NewsContext);

  const imageResult = async (result) => {
    if (!result) {
      return;
    }
    result = result.assets[0];
    const formData = new FormData();
    formData.append('image', {
      uri: result.uri,
      type: result.type,
      name: result.fileName,
    });
    const res = await uploadImage(formData);
    console.log('ImageResult error: ',res);
    setImage(res.path);
  };

  const handleChoosePhoto = useCallback(() => {
    const options = {
      saveToPhotos: true,
      mediaType: "photo",
      includeBase64: false,
      includeExtra: true,
    };
    launchCamera(options, imageResult);
  }, []);

  const handleSubmit = useCallback(async () => {
    const res = await saveNews(
      title,
      content,
      image
    );
    console.log('>>>>>>>>>>>>>>>>>>>>>callback',res);
    if (res) {
      Alert.alert("Success", "News has been added");
      setContent("");
      setTitle("");
      setImage(null);
    } else {
      Alert.alert("Error", "Something went wrong");
    }
  }, [title, content, image]);
  return (
    <View style={styles.container}>
      {image ? (
        <Image source={{ uri: image }} style={styles.coverPhotos} />
      ) : (
        <Pressable onPress={handleChoosePhoto} style={styles.coverPhotos}>
          <Image
            source={require("../../../../../src/media/images/plus.png")}
            style={styles.plus}
          />
          <Text style={styles.addCoverPhoto}>Add Cover Photo</Text>
        </Pressable>
      )}

      <TextInput
        style={styles.title}
        placeholder="Title"
        value={title}
        onChangeText={setTitle}
      />

      <TextInput
        style={styles.title}
        placeholder="Content"
        numberOfLines={4}
        multiline={true}
        value={content}
        onChangeText={setContent}
      />

      <Pressable onPress={handleSubmit}>
        <Text>Publish</Text>
      </Pressable>
    </View>
  );
};

export default Add;

const styles = StyleSheet.create({
  title: {
    width: "100%",
    height: 36,
    borderBottomColor: "#c4c4c4",
    borderBottomWidth: 1,
    marginTop: 16,
  },
  plus: {
    width: 24,
    height: 24,
    marginBottom: 20,
  },
  addCoverPhoto: {},
  coverPhotos: {
    width: "100%",
    height: 183,
    backgroundColor: "eef1f4",
    borderRadius: 6,
    borderWidth: 1,
    borderStyle: "dashed",
    justifyContent: "center",
    alignItems: "center",
  },
  container: {
    flex: 1,
    backgroundColor: "#fff",
    padding: 24,
  },
});
