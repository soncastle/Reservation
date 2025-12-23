import React, { useEffect } from "react";
import "../styles/Tailwind.css";

declare global {
  interface Window {
    kakao: any;
  }
}

const Map: React.FC = () => {
  useEffect(() => {
    if (document.getElementById("kakao-map-script")) {
      loadMap();
      return;
    }

    const script = document.createElement("script");
    script.id = "kakao-map-script";
    script.src =
      "https://dapi.kakao.com/v2/maps/sdk.js?appkey=089432a6cc352331beea83a6605c0a78&autoload=false";
    script.async = true;
    document.head.appendChild(script);

    script.onload = loadMap;
  }, []);

  const loadMap = () => {
    if (!window.kakao || !window.kakao.maps) return;

    window.kakao.maps.load(() => {
      const container = document.getElementById("map");
      if (!container) return;

      const position = new window.kakao.maps.LatLng(
        37.709806595488146,
        126.76429362295215
      );

      const map = new window.kakao.maps.Map(container, {
        center: position,
        level: 3,
      });

      new window.kakao.maps.Marker({ position }).setMap(map);

      const content = `
        <div style="position:relative; transform:translateY(-8px);">
          <div style="
            background:white;
            padding:8px 14px;
            border-radius:6px;
            border:1px solid #333;
            font-weight:600;
            text-align:center;
            white-space:nowrap;
          ">
            마이리솔
          </div>

          <!-- 꼬리 -->
          <div style="
            position:absolute;
            left:50%;
            transform:translateX(-50%);
            bottom:-8px;
            border-left:7px solid transparent;
            border-right:7px solid transparent;
            border-top:8px solid white;
          "></div>

          <!-- 꼬리 테두리 -->
          <div style="
            position:absolute;
            left:50%;
            transform:translateX(-50%);
            bottom:-9px;
            border-left:8px solid transparent;
            border-right:8px solid transparent;
            border-top:9px solid #333;
            z-index:-1;
          "></div>
        </div>
      `;

      const overlay = new window.kakao.maps.CustomOverlay({
        content,
        position,
        yAnchor: 1,
      });

      overlay.setMap(map);
    });
  };

  return (
    <div>
      <h1 className="mb-5 mt-5">솔이네 위치</h1>
      <div id="map" className="mb-5 mx-auto rounded-xl" style={{ width: "95%", height: "500px" }} />
    </div>
  );
};

export default Map;
