import { NextResponse } from "next/server";
import type { NextRequest } from "next/server";

export function middleware(req: NextRequest) {
  const token = req.cookies.get("token")?.value || null;

  // Se tentar acessar rota protegida sem token → redireciona para login
  if (req.nextUrl.pathname.startsWith("/dashboard") && !token) {
    return NextResponse.redirect(new URL("/login", req.url));
  }

  return NextResponse.next();
}

// Define quais rotas serão protegidas
export const config = {
  matcher: ["/dashboard/:path*"],
};
