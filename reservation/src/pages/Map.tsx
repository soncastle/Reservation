import React, { useEffect } from "react";

const Map: React.FC = () => {
  useEffect(() => {
    // 카카오맵 스크립트 로드
    const script = document.createElement("script");
    script.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=f6a7620121c2facb0292961f283a5a11`;
    script.async = true;
    script.onload = () => {
      if (window.kakao) {
        const kakao = window.kakao;
        const container = document.getElementById("map");
        const options = {
          center: new kakao.maps.LatLng(37.5665, 126.9780), // 서울 시청
          level: 3,
        };
        const map = new kakao.maps.Map(container, options);

        // 마커 추가
        const marker = new kakao.maps.Marker({
          position: new kakao.maps.LatLng(37.5665, 126.9780),
        });
        marker.setMap(map);

        // 인포윈도우 추가
        const infowindow = new kakao.maps.InfoWindow({
          content: "<div style='padding:5px;'>서울 시청</div>",
        });
        infowindow.open(map, marker);
      }
    };
    document.head.appendChild(script);
  }, []);

  return (
    <div>
      <h2>카카오 지도 (TypeScript)</h2>
      <div id="map" style={{ width: "100%", height: "500px" }}></div>
    </div>
  );
};

export default Map;
