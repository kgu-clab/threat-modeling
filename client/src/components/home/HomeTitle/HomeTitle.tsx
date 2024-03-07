const HomeTitle = () => {
  return (
    <div className="relative flex flex-col items-center justify-center gap-5 py-16 my-10 text-center">
      <div className="absolute square-pattern"></div>
      <div className="flex justify-center gap-2 text-5xl font-bold leading-normal">
        <h1 className="text-nowrap">
          <span>🛡️ </span>
          <span className="text-transparent bg-gradient-to-b from-slate-500 via-slate-700 to-black bg-clip-text">
            Threat Modeling
          </span>
        </h1>
        <span className="pt-2 text-xs text-sky-500">BETA</span>
      </div>
      <div className="max-w-xl space-y-2 break-keep">
        <h2 className="text-xl font-semibold underline-offset-4">
          보안 위협을&nbsp;
          <u className="decoration-dashed">미리 파악하고 체계적</u>으로 대응해
          보세요.
        </h2>
        <h3 className="text-sm text-gray-600">
          단 한 개의 JSON 파일로 시작되는 철저한 보안 진단. MITRE와 NIST 기준에
          따른 위협 분석을 통해 보안 취약성과 대응 방안을 정리하여 알려드릴게요.
        </h3>
      </div>
    </div>
  );
};

export default HomeTitle;
