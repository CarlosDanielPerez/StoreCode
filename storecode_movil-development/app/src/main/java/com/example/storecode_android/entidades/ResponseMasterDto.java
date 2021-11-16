package com.example.storecode_android.entidades;

import androidx.annotation.NonNull;

@SuppressWarnings("ALL")
public class ResponseMasterDto <T>{

	private T payLoad;
	private DetailResponseDto detailResponse;

	public T getPayLoad() {
		return payLoad;
	}

	public void setPayLoad(T payLoad) {
		this.payLoad = payLoad;
	}

	public DetailResponseDto getDetailResponse() {
		return detailResponse;
	}

	public void setDetailResponse(DetailResponseDto detailResponse) {
		this.detailResponse = detailResponse;
	}

	@NonNull
	@Override
	public String toString() {
		return "ResponseMasterDto{" +
				"payLoad=" + payLoad +
				", detailResponse=" + detailResponse +
				'}';
	}
}