import { axiosCommonInstance } from '@/apis/axiosInstance';
import { APIResponse, IProblemRes } from '@/types/model';
import { IGetProbListParams, IProbDetailInfo, ICodePatchParams } from '@/types/problem';

export const getProbList = async (
  params: IGetProbListParams
): Promise<APIResponse<IProblemRes>> => {
  try {
    const queryString = new URLSearchParams({
      page: params.page.toString(),
      size: params.size.toString(),
      ...(params.start && { start: params.start.toString() }),
      ...(params.end && { end: params.end.toString() }),
      ...(params.keyword && { keyword: params.keyword }),
      ...Object.fromEntries(params.tag?.map((tag, index) => [`tag[${index}]`, tag]) || []),
    }).toString();

    const res = await axiosCommonInstance.get(`/problems?${queryString}`);
    console.log('service gotten : ', res);
    return res.data;
  } catch (error) {
    console.error('서비스 에러!', error);
    throw error;
  }
};

export const getProbDetail = async (problemNo: number): Promise<APIResponse<IProbDetailInfo>> => {
  try {
    const res = await axiosCommonInstance.get(`/problems/${problemNo}`);
    console.log('service gotten : ', res);
    return res.data;
  } catch (error) {
    console.error('서비스 에러!', error);
    throw error;
  }
};

export const deleteCode = async (codeId: number): Promise<void> => {
  try {
    await axiosCommonInstance.delete(`/problems/code/${codeId}`);
    console.log('code deleted : ', codeId);
  } catch (error) {
    console.error('코드 삭제 에러!', error);
    throw error;
  }
};

export const patchCode = async (codeId: number, params: ICodePatchParams): Promise<void> => {
  try {
    await axiosCommonInstance.patch(`/problems/code/${codeId}`, {
      name: params.name,
      reviewStatus: params.reviewStatus,
    });
    console.log(`코드 업데이트 : ${codeId}, ${params.name}, ${params.reviewStatus}`);
  } catch (error) {
    console.error('코드 업데이트 에러!', error);
    throw error;
  }
};
