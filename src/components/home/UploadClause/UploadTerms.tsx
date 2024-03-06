import { Link } from "react-router-dom";

const UploadTerms = () => {
  return (
    <div className="flex flex-col space-y-2">
      <label className="font-bold">이용 약관 동의서</label>
      <p className="h-40 p-2 overflow-auto border">
        &nbsp;본 이용 약관(이하 '약관')은 TM (이하 '서비스')를 이용함에 있어
        DSLab(이하 '연구실', '우리')와 이용자(이하 '사용자', '귀하') 간의 관계를
        정의합니다. 서비스를 이용하기 위해 사용자는 아래의 약관에 동의해야
        합니다.
        <br />
        <br />
        <span className="font-semibold">1. 데이터 사용 목적</span>
        <br />
        &nbsp;귀하가 업로드하는 데이터는 다음과 같은 목적으로 사용됩니다: - 위협
        흐름에 따라 attack id와 관련된 cve, cvss, mitigation id, defend id,
        control id 등을 매핑하여 결과 리포트를 생성합니다.
        <br />
        &nbsp;- 생성된 리포트를 통해 서비스의 기능 개선을 위한 연구를
        진행합니다.
        <br />
        &nbsp;- 차후 연구 목적으로 데이터를 활용할 수 있습니다.
        <br />
        <br />
        <span className="font-semibold">2. 데이터의 종류 및 수집 방법</span>
        <br />
        &nbsp;- 사용자가 업로드하는 JSON 파일.
        <br />
        &nbsp;- 업로드 시점에 JSON 파일이 서버에 저장됩니다.
        <br />
        <br />
        <span className="font-semibold">3. 데이터 공유 및 공개</span>
        <br />
        &nbsp;- 연구 목적으로 데이터를 이용할 경우, 결과물은 예시로서 제3자와
        공유 및 공개될 수 있습니다. 단, 이는 비사업적 목적으로만 제한됩니다.
        <br />
        <br />
        <span className="font-semibold">4. 사용자 권리</span>
        <br />
        <br />
        &nbsp;- 사용자는 언제든지 자신이 제공한 데이터에 대한 접근, 수정, 삭제를
        요청할 수 있습니다.
        <br />
        &nbsp;- 이러한 요청은 DSLab를 통해 진행할 수 있습니다.
        (zxx52539@kyonggi.ac.kr){" "}
        <Link className="underline" to={"https://sites.google.com/view/ksel"}>
          DS Lab
        </Link>
        .
        <br />
        <br />
        <span className="font-semibold">5. 데이터 보호 및 보안</span>
        <br />
        &nbsp;- 연구실에서는 사용자 데이터의 보안과 보호를 위해 최선의 기술적,
        관리적 조치를 취합니다.
        <br />
        &nbsp;- 데이터는 암호화 및 안전한 저장 방법을 통해 관리됩니다.
        <br />
        <br />
        <span className="font-semibold">6. 약관의 변경</span>
        <br />
        &nbsp;- 연구실에서는 필요한 경우 약관을 변경할 수 있으며, 변경된 약관은
        서비스를 통해 공지됩니다.
        <br />
        &nbsp;- 약관 변경 후 서비스 이용을 계속하시면 변경된 약관에 동의한
        것으로 간주됩니다.
      </p>
    </div>
  );
};

export default UploadTerms;
