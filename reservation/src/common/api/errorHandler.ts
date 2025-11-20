import { error } from "console";

export interface ApiError {
    status : number,
    code : string,
    message : string
}

export function handleApiError(error:any): ApiError {
 
    if(!error.response){
        return {
            status : 0,
            code : "NETWORK_ERROR",
            message : "서버와 통신할 수 없습니다."
        };
    };

       const {status, data} = error.response;

    return {
        status : status,
        code : data.code ?? "UNCKECKED_ERROR",
        message : data.message ?? "알 수 없는 오류가 발생했습니다."
    }
}

