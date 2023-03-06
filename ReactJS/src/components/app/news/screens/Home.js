import {
  StyleSheet,
  Text,
  View,
  Image,
  ScrollView,
  FlatList,
  Pressable,
} from 'react-native';
import React, {useState, useContext, useEffect} from 'react';
import {NewsContext} from '../utilities/NewsContext';

const Home = props => {
  const {navigation} = props;
  const {getNews} = useContext(NewsContext);
  const [data, setData] = useState([]);

  const [refresh, setRefresh] = useState(false);

  const refreshData = async () => {
    setRefresh(true);
    const result = await getNews();
    setData(result);
    setRefresh(false);
  };
  useEffect(() => {
    const getData = async () => {
      const result = await getNews();
      setData(result);
    };
    getData();
    return () => {};
  }, []);
  const renderItem = ({item}) => {
    const {title, image, createdAt, _id} = item;

    const displayDate = value => {
      const date = new Date(value);
      const day = date.getDay();
      const month = date.getMonth() + 1;
      const year = date.getFullYear();
      return `${day}/${month}/${year}`;
    };

    return (
      <Pressable
        onPress={() => navigation.navigate('Detail', {id: _id})}
        style={homeStyles.card}>
        <Image source={{uri: image}} style={homeStyles.image} />

        <View style={homeStyles.information}>
          <Text style={homeStyles.category}>Europe</Text>
          <Text style={homeStyles.title}>{title}</Text>

          <View style={homeStyles.bottom}>
            <View style={homeStyles.left}>
              <Image
                style={homeStyles.icon}
                source={require('../../../../../src/media/images/Ellipse.png')}
              />
              <Text style={homeStyles.channel}>BBC News</Text>
            </View>
            <View style={homeStyles.right}>
              <Image
                style={homeStyles.icon}
                source={require('../../../../../src/media/images/time.png')}
              />
              <Text style={homeStyles.time}>{displayDate(createdAt)}</Text>
            </View>
          </View>
        </View>
      </Pressable>
    );
  };
  return (
    <View style={homeStyles.container}>
      <Image source={require('../../../../../src/media/images/appicon.png')} />
      <View style={homeStyles.text1}>
        <Text style={homeStyles.textTrending}>Trending</Text>
        <Text style={homeStyles.textSeeall}>See all</Text>
      </View>
      <FlatList
        data={data}
        renderItem={renderItem}
        keyExtractor={item => item._id}
        showsVerticalScrollIndicator={false} // ẩn thanh cuộn
        refreshing = {refresh}
        onRefresh={refreshData}
      />
    </View>
  );
};

export default Home;

const homeStyles = StyleSheet.create({
  textTrending: {
    marginTop: 10,
    fontWeight: '600',
    fontSize: 16,
    lineHeight: 24,
    letterSpacing: 0.12,
    color: '#000000',
  },
  textSeeall: {
    position: 'absolute',
    right: 0,
    fontWeight: '400',
    fontSize: 14,
    lineHeight: 21,
    letterSpacing: 0.12,
  },
  time: {
    fontWeight: '400',
    fontSize: 13,
    lineHeight: 20,
    letterSpacing: 0.12,
    color: '#4e4b66',
  },

  channel: {
    fontWeight: '600',
    fontSize: 13,
    lineHeight: 20,
    letterSpacing: 0.12,
    color: '#4e4b66',
  },
  icon: {
    alignItems: 'center',
    marginRight: 4,
  },
  right: {
    marginLeft: 9.17,
    alignItems: 'center',
    flexDirection: 'row',
  },
  left: {
    alignItems: 'center',
    flexDirection: 'row',
  },
  bottom: {
    flexDirection: 'row',
    justifyContent: 'flex-start',
  },
  title: {
    fontWeight: '400',
    fontSize: 16,
    lineHeight: 24,
    color: '#000',
  },
  category: {
    fontWeight: '400',
    fontSize: 13,
    lineHeight: 20,
    color: '#4e4b66',
  },
  information: {
    flex: 1,
    height: 86,
  },
  image: {
    width: 86,
    height: 86,
    borderRadius: 6,
    marginRight: 4,
  },
  card: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 16,
    padding: 8,
  },
  container: {
    //flex: 1,
    backgroundColor: '#fff',
    padding: 24,
  },
});

//dữ liệu ảo
// var data = [
//   {
//     _id: "63bfa809c4f47f0016aee205",
//     title: "Phasellus in felis. Donec semper sapien a libero. Nam dui.",
//     content:
//       "Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.",
//     image:
//       "https://cdn.tuoitre.vn/2022/8/21/z36603545985729afe273eca0b86d68c30c8be6d894cd1-16610703194001184506404.jpg",
//     createdAt: "2023-01-12T06:26:17.539Z",
//     createdBy: {
//       _id: "63ac39aeedf7c80016c57a67",
//       name: "",
//       avatar: "",
//     },
//   },
//   {
//     _id: "63bfa809c4f47f0016aee206",
//     title: "Chúc mừng năm mới",
//     content:
//       "Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.",
//     image:
//       "https://cdn.tuoitre.vn/2022/8/21/z36603545985729afe273eca0b86d68c30c8be6d894cd1-16610703194001184506404.jpg",
//     createdAt: "2023-01-12T06:26:17.539Z",
//     createdBy: {
//       _id: "63ac39aeedf7c80016c57a67",
//       name: "",
//       avatar: "",
//     },
//   },
//   {
//     _id: "63bfa809c4f47f0016aee207",
//     title: "Chúc mừng sinh nhật",
//     content:
//       "Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.",
//     image:
//       "https://cdn.tuoitre.vn/2022/8/21/z36603545985729afe273eca0b86d68c30c8be6d894cd1-16610703194001184506404.jpg",
//     createdAt: "2023-01-12T06:26:17.539Z",
//     createdBy: {
//       _id: "63ac39aeedf7c80016c57a67",
//       name: "",
//       avatar: "",
//     },
//   },
//   {
//     _id: "63bfa809c4f47f0016aee208",
//     title: "Giáng sinh vui vẻ",
//     content:
//       "Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.",
//     image:
//       "https://cdn.tuoitre.vn/2022/8/21/z36603545985729afe273eca0b86d68c30c8be6d894cd1-16610703194001184506404.jpg",
//     createdAt: "2023-01-12T06:26:17.539Z",
//     createdBy: {
//       _id: "63ac39aeedf7c80016c57a67",
//       name: "",
//       avatar: "",
//     },
//   },
//   {
//     _id: "63bfa809c4f47f0016aee208",
//     title: "Giáng sinh vui vẻ",
//     content:
//       "Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.",
//     image:
//       "https://cdn.tuoitre.vn/2022/8/21/z36603545985729afe273eca0b86d68c30c8be6d894cd1-16610703194001184506404.jpg",
//     createdAt: "2023-01-12T06:26:17.539Z",
//     createdBy: {
//       _id: "63ac39aeedf7c80016c57a67",
//       name: "",
//       avatar: "",
//     },
//   },
//   {
//     _id: "63bfa809c4f47f0016aee208",
//     title: "Giáng sinh vui vẻ",
//     content:
//       "Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.",
//     image:
//       "https://cdn.tuoitre.vn/2022/8/21/z36603545985729afe273eca0b86d68c30c8be6d894cd1-16610703194001184506404.jpg",
//     createdAt: "2023-01-12T06:26:17.539Z",
//     createdBy: {
//       _id: "63ac39aeedf7c80016c57a67",
//       name: "",
//       avatar: "",
//     },
//   },
//   {
//     _id: "63bfa809c4f47f0016aee208",
//     title: "Giáng sinh vui vẻ",
//     content:
//       "Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.",
//     image:
//       "https://cdn.tuoitre.vn/2022/8/21/z36603545985729afe273eca0b86d68c30c8be6d894cd1-16610703194001184506404.jpg",
//     createdAt: "2023-01-12T06:26:17.539Z",
//     createdBy: {
//       _id: "63ac39aeedf7c80016c57a67",
//       name: "",
//       avatar: "",
//     },
//   },
//   {
//     _id: "63bfa809c4f47f0016aee208",
//     title: "Giáng sinh vui vẻ",
//     content:
//       "Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.",
//     image:
//       "https://cdn.tuoitre.vn/2022/8/21/z36603545985729afe273eca0b86d68c30c8be6d894cd1-16610703194001184506404.jpg",
//     createdAt: "2023-01-12T06:26:17.539Z",
//     createdBy: {
//       _id: "63ac39aeedf7c80016c57a67",
//       name: "",
//       avatar: "",
//     },
//   },
//   {
//     _id: "63bfa809c4f47f0016aee208",
//     title: "Giáng sinh vui vẻ",
//     content:
//       "Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque.",
//     image:
//       "https://cdn.tuoitre.vn/2022/8/21/z36603545985729afe273eca0b86d68c30c8be6d894cd1-16610703194001184506404.jpg",
//     createdAt: "2023-01-12T06:26:17.539Z",
//     createdBy: {
//       _id: "63ac39aeedf7c80016c57a67",
//       name: "",
//       avatar: "",
//     },
//   },

// ];
