import React, { useEffect } from "react";

declare global {
  interface Window {
    kakao: any;
  }
}

const Map: React.FC = () => {
  useEffect(() => {
    // 이미 로드된 스크립트 중복 방지
    const existingScript = document.getElementById("kakao-map-script");
    if (existingScript) return;

    const script = document.createElement("script");
    script.id = "kakao-map-script";
    script.src = "https://dapi.kakao.com/v2/maps/sdk.js?appkey=f6a7620121c2facb0292961f283a5a11&autoload=false";
    script.async = true;
    document.head.appendChild(script);

    script.onload = () => {
      window.kakao.maps.load(() => {
        const container = document.getElementById("map");
        if (!container) return;

        const options = {
          center: new window.kakao.maps.LatLng(37.5665, 126.9780),
          level: 3,
        };

        const map = new window.kakao.maps.Map(container, options);

        const marker = new window.kakao.maps.Marker({
          position: new window.kakao.maps.LatLng(37.5665, 126.9780),
        });
        marker.setMap(map);

        const infowindow = new window.kakao.maps.InfoWindow({
          content: "<div style='padding:5px;'>서울 시청</div>",
        });
        infowindow.open(map, marker);
      });
    };
  }, []);

  return (
    <div>
      <h2>카카오 지도</h2>
      <div id="map" style={{ width: "100%", height: "500px" }}></div>
    </div>
  );
};

export default Map;
