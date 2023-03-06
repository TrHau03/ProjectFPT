import React, {createContext} from 'react'
import AxiosInstance from '../../axiosClient/AxiosInstance';

export const NewsContext = createContext();

export const NewsProvider = (props) =>{
    const {children} = props;
    //get danh sách bài viết
    const getNews = async() =>{
        try {
            const result = await AxiosInstance().get('/articles')
            return result.data
        } catch (error) {
            console.log('getNews Error: ' ,error);
        }
        return [];
    }
    //get infor chi tiết bài viết
    const getDetail = async(id) =>{
        try {
            const response = await AxiosInstance().get(`/articles/${id}/detail`);
            return response.data[0];
        } catch (error) {
            console.log('get detail Error: ' ,error);
        }
        return null;
    }
    //Upload hình ảnh lên sever
    const uploadImage = async(formData) =>{
        try {
            const response = await AxiosInstance('multipart/form-data').post(`/media/upload`, formData);
            return response.data;
        } catch (error) {
            console.log('getUpload Error: ' ,error);
        }
        return null;
    }

    //Lưu bài viết
    const saveNews = async(title, content, image) =>{
        try {
            const body = {
                title: title,
                content: content,
                image: image,
            }
            console.log(body);
            const response =  await AxiosInstance().post('/articles', body);
            return true;
        } catch (error) {
            console.log('SaveNews: ' , error)
        }
        return false;
    }
    return (
        <NewsContext.Provider 
            value = {{getNews, getDetail,uploadImage,saveNews}}>
            {children}
        </NewsContext.Provider>
    )
}