/*
 * mygame.cpp: ���ɮ��x�C��������class��implementation
 * Copyright (C) 2002-2008 Woei-Kae Chen <wkc@csie.ntut.edu.tw>
 *
 * This file is part of game, a free game development framework for windows.
 *
 * game is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * game is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * History:
 *   2002-03-04 V3.1
 *          Add codes to demostrate the use of CMovingBitmap::ShowBitmap(CMovingBitmap &).
 *	 2004-03-02 V4.0
 *      1. Add CGameStateInit, CGameStateRun, and CGameStateOver to
 *         demonstrate the use of states.
 *      2. Demo the use of CInteger in CGameStateRun.
 *   2005-09-13
 *      Rewrite the codes for CBall and CEraser.
 *   2005-09-20 V4.2Beta1.
 *   2005-09-29 V4.2Beta2.
 *      1. Add codes to display IDC_GAMECURSOR in GameStateRun.
 *   2006-02-08 V4.2
 *      1. Revise sample screens to display in English only.
 *      2. Add code in CGameStateInit to demo the use of PostQuitMessage().
 *      3. Rename OnInitialUpdate() -> OnInit().
 *      4. Fix the bug that OnBeginState() of GameStateInit is not called.
 *      5. Replace AUDIO_CANYON as AUDIO_NTUT.
 *      6. Add help bitmap to CGameStateRun.
 *   2006-09-09 V4.3
 *      1. Rename Move() and Show() as OnMove and OnShow() to emphasize that they are
 *         event driven.
 *   2006-12-30
 *      1. Bug fix: fix a memory leak problem by replacing PostQuitMessage(0) as
 *         PostMessage(AfxGetMainWnd()->m_hWnd, WM_CLOSE,0,0).
 *   2008-02-15 V4.4
 *      1. Add namespace game_framework.
 *      2. Replace the demonstration of animation as a new bouncing ball.
 *      3. Use ShowInitProgress(percent) to display loading progress. 
 *   2010-03-23 V4.6
 *      1. Demo MP3 support: use lake.mp3 to replace lake.wav.
*/

#include "stdafx.h"
#include "Resource.h"
#include <mmsystem.h>
#include <ddraw.h>
#include "audio.h"
#include "gamelib.h"
#include "mygame.h"


namespace game_framework {
/////////////////////////////////////////////////////////////////////////////
// �o��class���C�����C���}�Y�e������
/////////////////////////////////////////////////////////////////////////////

CGameStateInit::CGameStateInit(CGame *g)
: CGameState(g)
{
}

void CGameStateInit::OnInit()
{
	//
	// ���ϫܦh�ɡAOnInit���J�Ҧ����ϭn��ܦh�ɶ��C���קK���C�����H
	//     �������@�СA�C���|�X�{�uLoading ...�v�A���Loading���i�סC
	//
	ShowInitProgress(0);	// �@�}�l��loading�i�׬�0%
	//
	// �}�l���J���
	//
	begin.LoadBitmap();
	about.LoadBitmap(IDB_ABOUT);
    // ��C�A�H�K�ݲM���i�סA��ڹC���ЧR����Sleep
	//
	// ��OnInit�ʧ@�|����CGameStaterRun::OnInit()�A�ҥH�i���٨S��100%
	//
}

void CGameStateInit::OnBeginState()
{	
}

void CGameStateInit::OnKeyUp(UINT nChar, UINT nRepCnt, UINT nFlags)
{
	const char KEY_ESC = 27;
	const char KEY_SPACE = ' ';
	
		if (begin.logoStop == 5)
		{
			BeginAbout++;
			if (nChar == KEY_SPACE && BeginAbout == 2)
			{
				Sleep(500);

				CAudio::Instance()->Stop(AUDIO_Menu_Intro);
				CAudio::Instance()->Play(AUDIO_Opening_Theme, false);
				GotoGameState(GAME_STATE_RUN);						// ������GAME_STATE_RUN	
			}
			else if (nChar == KEY_ESC)								// Demo �����C������k
				PostMessage(AfxGetMainWnd()->m_hWnd, WM_CLOSE, 0, 0);	// �����C��

		}
}

void CGameStateInit::OnMove()
{
	begin.OnMove();
}

void CGameStateInit::OnShow()
{
	CDC *pDC = CDDraw::GetBackCDC();			// ���o Back Plain �� CDC
	CFont f, *fp, *fp1, *fp2, *fp3, *fp4, *fp5, *fp6;
	f.CreatePointFont(100, "Times New Roman");	// ���� font f; 160����16 point���r
	fp = pDC->SelectObject(&f);					// ��� font f
	fp1 = pDC->SelectObject(&f);
	fp2 = pDC->SelectObject(&f);
	fp3 = pDC->SelectObject(&f);
	fp4 = pDC->SelectObject(&f);
	fp5 = pDC->SelectObject(&f);
	fp6 = pDC->SelectObject(&f);
	pDC->SetBkColor(RGB(0, 0, 0));
	pDC->SetTextColor(RGB(255, 255, 255));
	char str[80];								// Demo �Ʀr��r�ꪺ�ഫ
	char str1[80];
	char str2[80];
	char str3[80];
	char str4[80];
	char str5[80];
	char str6[80];
	sprintf(str3, "�ާ@����");
	sprintf(str4, "���@      ������");
	sprintf(str5, "���@      �k����");
	sprintf(str6, "Space�@�����W�O���D");

	sprintf(str, "�K��");
	sprintf(str1, "���@       �a�ϩ��W");
	sprintf(str2, " ���@      �a�ϩ��U");		
	pDC->TextOut(120, 400, str3);
	pDC->SelectObject(fp3);						
	pDC->TextOut(100, 430, str4);
	pDC->SelectObject(fp4);					
	pDC->TextOut(100, 460, str5);
	pDC->SelectObject(fp5);					
	pDC->TextOut(100, 490, str6);
	pDC->SelectObject(fp6);				

	pDC->TextOut(140, 540, str);
	pDC->SelectObject(fp);						// �� font f (�d�U���n�|�F��)
	pDC->TextOut(100, 570, str1);
	pDC->SelectObject(fp1);
	pDC->TextOut(100, 600, str2);
	pDC->SelectObject(fp2);
	CDDraw::ReleaseBackCDC();					// �� Back Plain �� CDC

	if (BeginAbout == 0)
	{
		begin.OnShow();
	}
	else if (BeginAbout == 1)
	{
		about.SetTopLeft(0, 0);
		about.ShowBitmap();
	}
	//GotoGameState(GAME_STATE_RUN);
}								

/////////////////////////////////////////////////////////////////////////////
// �o��class���C�����������A(Game Over)
/////////////////////////////////////////////////////////////////////////////

CGameStateFinal::CGameStateFinal(CGame *g)
: CGameState(g)
{
}

void CGameStateFinal::OnMove()
{
	counter--;
	if (counter < 0)
		GotoGameState(GAME_STATE_INIT);
}

void CGameStateFinal::OnBeginState()
{
	counter = 30 * 5; // 5 seconds
}

void CGameStateFinal::OnInit()
{
	End.LoadBitmap(IDB_END);
	//
	// ���ϫܦh�ɡAOnInit���J�Ҧ����ϭn��ܦh�ɶ��C���קK���C�����H
	//     �������@�СA�C���|�X�{�uLoading ...�v�A���Loading���i�סC
	//
	ShowInitProgress(66);	// ���ӫe�@�Ӫ��A���i�סA���B�i�׵���66%
	//
	// �}�l���J���
	//
	Sleep(300);				// ��C�A�H�K�ݲM���i�סA��ڹC���ЧR����Sleep
	//
	// �̲׶i�׬�100%
	//
	ShowInitProgress(100);
}

void CGameStateFinal::OnShow()
{
	/*
	CDC *pDC = CDDraw::GetBackCDC();			// ���o Back Plain �� CDC
	CFont f,*fp;
	f.CreatePointFont(160,"Times New Roman");	// ���� font f; 160����16 point���r
	fp=pDC->SelectObject(&f);					// ��� font f
	pDC->SetBkColor(RGB(0,0,0));
	pDC->SetTextColor(RGB(255,255,0));
	char str[80];								// Demo �Ʀr��r�ꪺ�ഫ
	sprintf(str, "Game Over ! (%d)", counter / 30);
	pDC->TextOut(240,210,str);
	pDC->SelectObject(fp);						// �� font f (�d�U���n�|�F��)
	CDDraw::ReleaseBackCDC();					// �� Back Plain �� CDC
	*/
	End.ShowBitmap(2);
}

/////////////////////////////////////////////////////////////////////////////
// �o��class���C�����C�����檫��A�D�n���C���{�����b�o��
/////////////////////////////////////////////////////////////////////////////

CGameStateRun::CGameStateRun(CGame *g)
: CGameState(g)
{
	king = new King;
	map = new Map;
}

CGameStateRun::~CGameStateRun()
{
	delete king;
	delete map;
}

void CGameStateRun::OnBeginState()
{
	king->Initialize();
}

void CGameStateRun::OnMove()							// ���ʹC������
{
	if (map->IsEmpty(king->GetX(), king->GetY()) == 6)
	{
		GotoGameState(GAME_STATE_FINAL);
	}
	king->OnMove(map);
}

void CGameStateRun::OnInit()  								// �C������Ȥιϧγ]�w
{
	//
	// ���ϫܦh�ɡAOnInit���J�Ҧ����ϭn��ܦh�ɶ��C���קK���C�����H
	//     �������@�СA�C���|�X�{�uLoading ...�v�A���Loading���i�סC
	//
	ShowInitProgress(33);	// ���ӫe�@�Ӫ��A���i�סA���B�i�׵���33%
	//
	// �}�l���J���
	//
	map->LoadBitmap();
	king->LoadBitmap();
	CAudio::Instance()->Load(AUDIO_Menu_Intro, "sounds\\Menu_Intro.mp3");
	CAudio::Instance()->Load(AUDIO_Opening_Theme, "sounds\\Opening_Theme.mp3");
	CAudio::Instance()->Load(AUDIO_Fall, "sounds\\Fall.mp3"); // ���a����
	CAudio::Instance()->Load(AUDIO_Bump, "sounds\\Bump.mp3"); // �ϼu����
	//CAudio::Instance()->Play(AUDIO_Menu_Intro, true);

	//
	// ��������Loading�ʧ@�A�����i��
	//
	ShowInitProgress(50);
     // ��C�A�H�K�ݲM���i�סA��ڹC���ЧR����Sleep
	//
	// �~����J��L���
	//
	//
	// ��OnInit�ʧ@�|����CGameStaterOver::OnInit()�A�ҥH�i���٨S��100%
	//
}

void CGameStateRun::OnKeyDown(UINT nChar, UINT nRepCnt, UINT nFlags)
{
	const char KEY_LEFT  = 0x25;			// keyboard���b�Y
	const char KEY_RIGHT = 0x27;			// keyboard�k�b�Y
	const unsigned int KEY_SPACE = 229;		// keyboard�ť���

	if (nChar == KEY_LEFT)
	{
		king->SetMovingLeft(true);
		king->FaceDirection();
	}

	if (nChar == KEY_RIGHT)
	{
		king->SetMovingRight(true);
		king->FaceDirection();
	}

	if (nChar == KEY_SPACE) {
		king->addJumpForce();
	}


	//------------------------------����------------------------------//
	
	if (nChar == 87) {
		king->TestMove(4, map);
	}
	if (nChar == 65) {
		king->TestMove(2, map);
	}
	if (nChar == 83) {
		king->TestMove(3, map);
	}
	if (nChar == 68) {
		king->TestMove(1, map);
	}
	if (nChar == 38) {
		map->plus();
	}
	if (nChar == 40) {
		map->minus();
	}
	

}

void CGameStateRun::OnKeyUp(UINT nChar, UINT nRepCnt, UINT nFlags)
{
	const char KEY_LEFT  = 0x25; // keyboard���b�Y
	const char KEY_RIGHT = 0x27; // keyboard�k�b�Y
	const char KEY_SPACE = 0x20; // keyboard�ť���

	if (nChar == KEY_LEFT)
	{
		king->SetMovingLeft(false);
		king->FaceDirection();
	}
	
	if (nChar == KEY_RIGHT)
	{
		king->SetMovingRight(false);
		king->FaceDirection();
	}

	if (nChar == KEY_SPACE) {
		king->doJump();
	}
}

void CGameStateRun::OnShow()
{
	//
	//  �`�N�GShow�̭��d�U���n���ʥ��󪫥󪺮y�СA���ʮy�Ъ��u�@����Move���~��A
	//        �_�h���������sø�Ϯ�(OnDraw)�A����N�|���ʡA�ݰ_�ӷ|�ܩǡC���ӳN�y
	//        ���AMove�t�dMVC����Model�AShow�t�dView�A��View�������Model�C
	//
	//
	//  �K�W�I���ϡB�����ơB�y�B���l�B�u�����y
	//
	map->ShowBitmap();
	king->OnShow();
	//
	//  �K�W���W�Υk�U��������
	//
}
}