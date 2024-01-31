; csc 221-01
; project 7
; Turn entered string to all CAPS
; Antonio Rodriguez
; 11/13/20

INCLUDE Irvine32.inc
INCLUDE Macros.inc

MAX = 64
.data
    ask BYTE "Enter a string: ", 0
    source BYTE MAX DUP('#'),0

.code
main PROC
    mov edx, OFFSET ask             ; ask question
    call WriteString                ; print question
    mov edx, OFFSET source          ; where string will be stored
    mov ecx, LENGTHOF source        ; set counter to number of char's in string
    call ReadString                 ; get string
    call ToUpper                    ; call PROC to return upper case string
    mov edx, OFFSET source          ; receive the all upper case string
    call WriteString                ; print string
    exit
main ENDP

; Description: Convert each char in given buffer to upper case
; Receive: edx – address of buffer
; ecx – number of char in buffer to convert (NOT MAX)
; Returns: edx and ecx are returned to original values
; Requires: buffer to be MAX + 1 in length
ToUpper PROC uses EAX ECX EDX
    mov ecx, eax
    TOP:
        mov al, [edx]           ; move char to al register to compare
        cmp al, 'a'             ; if belwo 'a' it is NOT a (lower case letter)
        jb SKIP                 ; jump past conversion 
        cmp al, 'z'             ; if above 'z' it is NOT a (lower case letter) 
        ja SKIP                 ; jump past conversion
        and al, 11011111b       ; capitalize char
        mov [edx], al           ; replace lower case with upper cased char 

        SKIP:
        inc edx                 ; move to next char
    loop TOP
    ret
 ToUpper ENDP

END main