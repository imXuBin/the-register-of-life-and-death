import axios from 'axios'
axios.defaults.baseURL = process.env.REACT_APP_SERVER_URL

export const get = (url: string, data?: any) => axios.get(url, data)
export const post = (url: string, data?: any) => axios.post(url, data)
export const put = (url: string, data?: any) => axios.put(url, data)
export const del = (url: string, data?: any) => axios.delete(url, data)
export const patch = (url: string, data?: any) => axios.patch(url, data)